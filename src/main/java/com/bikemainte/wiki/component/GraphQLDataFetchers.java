package com.bikemainte.wiki.component;

import com.bikemainte.wiki.common.util.rsql.GenericRsqlSpecBuilder;
import com.bikemainte.wiki.entity.Category;
import com.bikemainte.wiki.repository.CategoryRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hongyu
 * @date 2:19 PM 15/4/2019
 */
@Service
public class GraphQLDataFetchers {
    @Autowired
    private CategoryRepository categoryRepository;

//    public DataFetcher<Category> getCategoryDataFetcher(){
//        return dataFetchingEnvironment -> {
//            Map<String,String> category=dataFetchingEnvironment.getSource();
//            String id=book
//            return categoryRepository.findById(categoryId).orElse(null);
//        };
//    }

    public DataFetcher<List<Category>> getCategoriesByRestQuery() {
        return dataFetchingEnvironment -> {
            String query = dataFetchingEnvironment.getArgument("query");
            return categoryRepository.findAll(GenericRsqlSpecBuilder.parse(query));
        };
    }
}
