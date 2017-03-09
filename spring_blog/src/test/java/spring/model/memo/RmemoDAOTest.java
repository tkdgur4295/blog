package spring.model.memo;

import static org.junit.Assert.*;

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

import spring.model.bbs.ReplyDAO;
import spring.model.bbs.ReplyDTO;

public class RmemoDAOTest {
	private static BeanFactory beans;
	private static RmemoDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClassPathResource resource =  new ClassPathResource("blog.xml");
		beans = new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao = (RmemoDAO)beans.getBean("rmdao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testRcount() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testCreate() {
		RmemoDTO dto = new RmemoDTO();
		dto.setMemono(1501);
		dto.setId("Jake");
		dto.setContent("Card Game?");
		
		assertTrue(dao.create(dto));
	}

	@Test @Ignore
	public void testRead() {
		RmemoDTO dto = dao.read(2);
		assertNotNull(dto);
	}

	@Test @Ignore
	public void testUpdate() {
		RmemoDTO dto = new RmemoDTO();
		dto.setMemono(1501);
		dto.setContent("OK ");
		dto.setId("Jake");
		dto.setRnum(2);
		
		assertTrue(dao.update(dto));
	}

	@Test @Ignore
	public void testList() {
		Map map = new HashMap();
		map.put("memono", 8);
		map.put("sno", 1);
		map.put("eno", 5);
		List<RmemoDTO> list = dao.list(map);
		assertEquals(list.size(), 1);
	}

	@Test @Ignore
	public void testTotal() {
		int memono = 8;
		int cnt = dao.total(memono);
		assertEquals(cnt, 1);
	}

	@Test @Ignore
	public void testDelete() {
		int rnum = 3;
		assertTrue(dao.delete(rnum));
	}

	@Test 
	public void testBdelete() throws Exception {
		int memo = 1;
		assertEquals(dao.bdelete(memo),1);
	}

}
