package spring.model.bbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BbsDAOTest {
	private static BeanFactory beans;
	private static BbsDAO bdao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClassPathResource resource = new ClassPathResource("blog.xml");
		beans = new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bdao = (BbsDAO)beans.getBean("bdao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testCheckRefno() {
		int bbsno = 2;
		assertTrue(bdao.checkRefno(bbsno));
	}

	@Test @Ignore
	public void testTotal() {
		assertEquals(bdao.total("wname", "finn"),1);
	}

	@Test @Ignore
	public void testUpAnsnum() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testCreateReply() {
		BbsDTO dto = bdao.readReply(9);
		dto.setWname("Jake");
		dto.setContent("Adventure Card Game");
		dto.setPasswd("1234");
		dto.setFilename("Jake.jpg");
		dto.setFilesize(100);
		
		Map map = new HashMap();
		map.put("greno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		
		bdao.upAnsnum(map);
		assertTrue(bdao.createReply(dto));
		
	}

	@Test @Ignore
	public void testDelete() {
		int bbsno = 2;
		assertTrue(bdao.delete(bbsno));
	}

	@Test @Ignore
	public void testUpdate() {
		BbsDTO dto = new BbsDTO();
		dto.setBbsno(14);
		dto.setWname("Finn");
		dto.setTitle("and Jake");
		dto.setContent("Adventure Time with Finn and Jake .page 19");
		dto.setFilename("jake.jpg");
		dto.setFilesize(200);
		assertTrue(bdao.update(dto));
	}

	@Test @Ignore
	public void testPassCheck() {
		Map map = new HashMap();
		map.put("bbsno", 14);
		map.put("passwd", 1234);
		
		assertTrue(bdao.passCheck(map));
		
	}

	@Test @Ignore
	public void testReadReply() {
		BbsDTO dto = bdao.readReply(9);
		assertEquals(dto.getGrpno(), 9);
		assertEquals(dto.getIndent(), 0);
		assertEquals(dto.getAnsnum(), 0);
		assertEquals(dto.getTitle(), "1의 답변");
		
	}

	@Test @Ignore
	public void testRead() {
		bdao.upViewcnt(2);
		BbsDTO dto = bdao.read(14);
		assertNotNull(dto);
	}

	@Test @Ignore
	public void testUpViewcnt() {
		fail("Not yet implemented");
	}

	@Test 
	public void testList() {
		Map map = new HashMap();
		map.put("col", "wname");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		
		List<BbsDTO> list = bdao.list(map);
		assertEquals(list.size(), 12);
		
	}

	@Test @Ignore
	public void testCreate() {
		BbsDTO dto = new BbsDTO();
		dto.setWname("Finn");
		dto.setTitle("and Jake");
		dto.setContent("Adventure Time with Finn and Jake ");
		dto.setPasswd("1234");
		dto.setFilename("jake.jpg");
		dto.setFilesize(100);
		
		assertTrue(bdao.create(dto));
	}

}
