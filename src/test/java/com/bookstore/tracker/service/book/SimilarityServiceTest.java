package com.bookstore.tracker.service.book;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@SpringBootTest
@ActiveProfiles("test")
class SimilarityServiceTest {

    private static final long VALID_BOOK_ID = 20443683;

    private static final long INVALID_BOOK_ID = 9867;

    private static final int NUMBER_OF_SIMILARITY = 2;

    @Autowired
    private SimilarityService similarityService;

    @Test
    void whenBookSimilarity_withValidBookId_thenReturnItemRecommendations() {

        // Prepare
        List<RecommendedItem> recommendedItems = similarityService.bookSimilarity(VALID_BOOK_ID, NUMBER_OF_SIMILARITY);

        // Verify
        assertThat(recommendedItems.size(), is(NUMBER_OF_SIMILARITY));
    }

    @Test
    void whenBookSimilarity_withInValidBookId_thenReturnNull() {

        // Prepare
        List<RecommendedItem> recommendedItems = similarityService.bookSimilarity(INVALID_BOOK_ID, NUMBER_OF_SIMILARITY);

        // Verify
        assertThat(recommendedItems, is(nullValue()));
    }
}