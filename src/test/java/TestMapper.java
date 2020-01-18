import com.teoan.blog.entity.Category;
import com.teoan.blog.entity.Tag;
import com.teoan.blog.mapper.ArticleCategoryRefMapper;
import com.teoan.blog.mapper.ArticleTagRefMapper;
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
public class TestMapper extends TestSpring {
    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired(required = false)
    private ArticleTagRefMapper articleTagRefMapper;


    @Test
    public void testDb(){
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        logger.info(articleTagRefMapper.deleteByTagId(100000));
    }
}
