import com.github.pagehelper.PageHelper;
import com.teoan.blog.entity.Category;
import com.teoan.blog.entity.Tag;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.mapper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private LinkMapper linkMapper;

    @Autowired(required = false)
    private MenuMapper menuMapper;

    @Autowired(required = false)
    private NoticeMapper noticeMapper;

    @Autowired(required = false)
    private OptionsMapper optionsMapper;

    @Autowired(required = false)
    private PageMapper pageMapper;

    @Autowired(required = false)
    private TagMapper tagMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Test
    public void testDb(){
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
//        logger.info(commentMapper.listComment());
//        logger.info(linkMapper.listLink(1));
//        logger.info(menuMapper.listMenu());
//        logger.info(noticeMapper.listNotice(1));
//        logger.info(optionsMapper.getOptions());
//        logger.info(pageMapper.listPage(1));
//        logger.info(tagMapper.countTag());
//        logger.info(userMapper.listUser());
//        logger.info(userMapper.getUserByEmail("admin@liuyanzhao.com"));
        PageHelper.startPage(1, 10);
        HashMap<String,Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        logger.info(articleMapper.findAll(criteria));

    }

}
