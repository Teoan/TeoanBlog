import com.teoan.blog.entity.Category;
import com.teoan.blog.mapper.ArticleCategoryRefMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 　 @author Teoan
 *
 * @date 2020/1/16 12:21
 */
@Service
public class TestArticleCategoryRef extends TestSpring {
    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;


    @Test
    public void testDb(){
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        List<Category> categories = articleCategoryRefMapper.listCategoryByArticleId(14);
        List<Integer> i= articleCategoryRefMapper.selectArticleIdByCategoryId(1);
        logger.info(i);
    }
}
