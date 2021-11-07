package com.bookstore.tracker.service.book;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public interface SimilarityService {

    List<RecommendedItem> bookSimilarity(long bookId, int numberOfSimilarity);
}
