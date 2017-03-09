package spring.model.memo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.HashMap;
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

public class MemoDAOTest {
	private static BeanFactory beans;
	private static MemoDAO mdao;

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
		mdao = (MemoDAO)beans.getBean("mdao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testTotal() {
		assertEquals(mdao.total("title", "Finn"),1);
	}

	@Test @Ignore
	public void testUpViewcnt() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testDelete() {
		int memono = 1502;
		assertTrue(mdao.delete(memono));
	}

	@Test @Ignore
	public void testUpdate() {
		MemoDTO dto = new MemoDTO();
		dto.setMemono(1501);
		dto.setTitle("Finn And Jake");
		dto.setContent("Adventure Time with Finn and Jake");
		assertTrue(mdao.update(dto));
		
	}

	@Test @Ignore
	public void testRead() {
		mdao.upViewcnt(1501);
		MemoDTO dto = mdao.read(1501);
		assertNotNull(dto);
	}

	@Test @Ignore
	public void testCreate() {
		MemoDTO dto = new MemoDTO();
		dto.setTitle("Finn");
		dto.setContent("And Jake");
		assertTrue(mdao.create(dto));
		
	}

	@Test @Ignore
	public void testList() {
		Map map = new HashMap();
		map.put("col", "title");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		List<MemoDTO>list = mdao.list(map);
		assertEquals(list.size(), 2);
	}

}
