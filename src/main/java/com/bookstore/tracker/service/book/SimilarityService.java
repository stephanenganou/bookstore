package com.bookstore.tracker.service.book;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (Book related) for classes which will implement it.
 */
@Service
public interface SimilarityService {

    /**
     * Method responsible for rendering a list a recommended books for a specific book (by its id).
     */
    List<RecommendedItem> bookSimilarity(final long bookId, final int numberOfSimilarity);
}
