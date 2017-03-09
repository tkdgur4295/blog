package spring.model.images;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.utility.blog.DBClose;
import spring.utility.blog.DBOpen;

@Repository
public class ImagesDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public void upViewcnt(int no) {
		sqlSession.update("member.upViewcnt", no);

	}

	public boolean checkRefno(int no) {
		boolean flag = false;

		int cnt = sqlSession.selectOne("images.checkRefno", no);
		if (cnt > 0) {
			flag = true;// 부모글이다.
		}

		return flag;
	}

	public void upAno(Map map) {
		sqlSession.update("images.upAno", map);
	}

	public boolean createReply(ImagesDTO dto) {
		boolean flag = false;

		int cnt = sqlSession.insert("images.createReply", dto);

		if (cnt > 0)
			flag = true;

		return flag;
	}

	public ImagesDTO readReply(int no) {

		return sqlSession.selectOne("images.readReply", no);
	}

	public int total(String col, String word) {
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);

		return sqlSession.selectOne("images.total", map);
	}

	public List<ImagesDTO> list(Map map) {

		return sqlSession.selectList("images.list", map);
	}

	public boolean passwd(Map map) {
		boolean flag = false;

		int cnt = sqlSession.selectOne("images.passwd", map);
		if (cnt > 0) {
			flag = true;
		}

		return flag;

	}

	public boolean delete(int no) {
		boolean flag = false;

		int cnt = sqlSession.delete("images.delete", no);
		if (cnt > 0) {
			flag = true;
		}

		return flag;
	}

	public boolean update(ImagesDTO dto) {
		boolean flag = false;

		int cnt = sqlSession.update("images.update", dto);

		if (cnt > 0) {
			flag = true;
		}

		return flag;

	}

	public boolean updateFile(ImagesDTO dto) {
		boolean flag = false;

		int cnt = sqlSession.update("images.updateFile", dto);

		if (cnt > 0) {
			flag = true;
		}

		return flag;

	}

	public Map sumnail(int no) {

		return sqlSession.selectOne("images.sumnail", no);

	}

	public ImagesDTO read(int no) {

		return sqlSession.selectOne("images.read", no);

	}

	public boolean create(ImagesDTO dto) {
		boolean flag = false;

		int cnt = sqlSession.insert("images.create", dto);
		if (cnt > 0) {
			flag = true;
		}

		return flag;

	}

}
