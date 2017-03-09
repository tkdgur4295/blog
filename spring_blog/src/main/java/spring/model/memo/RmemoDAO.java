package spring.model.memo;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.IReplyDAO;

@Repository
public class RmemoDAO implements IReplyDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int rcount(int memono) {
		return sqlSessionTemplate.selectOne("rmemo.rcount", memono);
	}

	public boolean create(RmemoDTO dto) {
		boolean flag = false;

		int cnt = (Integer) sqlSessionTemplate.insert("rmemo.create", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public RmemoDTO read(int rnum) {

		return sqlSessionTemplate.selectOne("rmemo.read", rnum);

	}

	public boolean update(RmemoDTO dto) {
		boolean flag = false;

		int cnt = sqlSessionTemplate.update("rmemo.update", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public List<RmemoDTO> list(Map map) {

		return sqlSessionTemplate.selectList("rmemo.list", map);
	}

	public int total(int memono) {
		return (Integer) sqlSessionTemplate.selectOne("rmemo.total", memono);
	}

	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = sqlSessionTemplate.delete("rmemo.delete", rnum);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	/** 하나의 글의 여러댓글들 삭제 */
	public int bdelete(int memono) throws Exception {
		return sqlSessionTemplate.delete("rmemo.bdelete", memono);

	}

}