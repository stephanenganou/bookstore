package com.bookstore.tracker.service.book;

import com.bookstore.tracker.data.dao.BookViewMappingDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This class is used to handle the mapping and tracking of book's views by users.
 */
@Service
@Slf4j
public class SimilarityServiceImpl implements SimilarityService {

    private final BookViewMappingDao bookViewMappingDao;

    private final BookViewMappingService bookViewMappingService;

    @Value("${data.file.path}")
    private String fileNamePath;

    @Autowired
    public SimilarityServiceImpl(final BookViewMappingDao bookViewMappingDao,
                                 final BookViewMappingService bookViewMappingService) {

        this.bookViewMappingDao = bookViewMappingDao;
        this.bookViewMappingService = bookViewMappingService;
    }

    /**
     * @see com.bookstore.tracker.service.book.SimilarityService#bookSimilarity(long, int)
     */
    @Override
    public List<RecommendedItem> bookSimilarity(final long bookId, final int numberOfSimilarity) {
        try {
            bookViewMappingService.writeFileLocally(fileNamePath,
                    bookViewMappingDao.findAll());

            return getBookRecommendations(bookId, numberOfSimilarity);

        } catch (IOException | TasteException e) {
            log.error("An Error occurred while writing the file locally: {}", e.getMessage());
            return null;
        }
    }

    private List<RecommendedItem> getBookRecommendations(final long bookId, final int numberOfSimilarity) throws IOException, TasteException {

        final DataModel dataModel = new FileDataModel(new File(fileNamePath));

        final ItemSimilarity itemSimilarity = new LogLikelihoodSimilarity(dataModel);

        final GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

        return recommender.mostSimilarItems(bookId, numberOfSimilarity);
    }

}
