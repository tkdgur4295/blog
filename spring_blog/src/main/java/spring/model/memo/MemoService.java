package spring.model.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {
	@Autowired
	private MemoDAO dao;
	@Autowired
	private RmemoDAO rmemo;

	public void delete(int memono) throws Exception {

		rmemo.bdelete(memono);//댓글들 삭제(자식레코드)
		dao.delete(memono);//부모글삭제(부모레코드)
	}
}