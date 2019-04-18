package template;

import me.muphy.template.dao.MemberDao;

import java.util.List;

/**
 * 2019/4/18
 * 莫非
 */
public class MemberDaoTest {
    public static void main(String[] args) throws Exception {
        MemberDao dao = new MemberDao(null);
        List<?> result = dao.selectAll();
        System.out.println(result);
    }
}
