package spring.sts.blog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.images.ImagesDAO;
import spring.model.images.ImagesDTO;
import spring.utility.blog.Utility;

@Controller
public class ImagesController {

	@Autowired
	private ImagesDAO dao;
	
	@RequestMapping(value = "images/delete",method=RequestMethod.POST)
	public String delete(Model model,HttpServletRequest request,int no,String passwd,String oldfile,String col, String word, String nowPage){
		String basePath = request.getRealPath("/image/storage");
		HashMap map = new HashMap();
		map.put("no", no);
		map.put("passwd", passwd);
		boolean pflag = dao.passwd(map);
		if(pflag){
			if(dao.delete(no)){
				Utility.deleteFile(basePath, oldfile);
				model.addAttribute("col", col);
				model.addAttribute("word", word);
				model.addAttribute("nowPage", nowPage);
				return "redirect:./list";
			}else{
				return "error";
			}
		}else{
			return "passwdError";
		}
		
	}
	@RequestMapping(value = "images/delete",method=RequestMethod.GET)
	public String delete(Model model,int no){
		model.addAttribute("flag", dao.checkRefno(no));
		return "/images/delete";
	}

	@RequestMapping(value = "images/reply", method = RequestMethod.POST)
	public String reply(Model model, HttpServletRequest request, ImagesDTO dto, String col, String word,
			String nowPage) {
		String basePath = request.getRealPath("/image/storage");

		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";
		if (filesize > 0) {
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);
		HashMap map = new HashMap();
		map.put("gno", dto.getGno());
		map.put("ano", dto.getAno());
		dao.upAno(map);
		boolean flag = dao.createReply(dto);

		if (flag) {
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		} else {
			return "error";
		}


	}

	@RequestMapping(value = "images/reply", method = RequestMethod.GET)
	public String reply(Model model, int no) {
		
		model.addAttribute("dto", dao.readReply(no));

		return "/images/reply";
	}

	@RequestMapping(value = "images/updateFile", method = RequestMethod.POST)
	public String updatefile(Model model, HttpServletRequest request, ImagesDTO dto, String oldfile, String col,
			String word, String nowPage) {
		String basePath = request.getRealPath("/image/storage");
		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";

		if (filesize > 0) {
			Utility.deleteFile(basePath, oldfile);
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);

		boolean flag = dao.updateFile(dto);

		if (flag) {
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "images/updateFile", method = RequestMethod.GET)
	public String updatefile(int no, Model model) {

		model.addAttribute("dto", dao.read(no));

		return "/images/updateFile";
	}

	@RequestMapping(value = "/images/update", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request, ImagesDTO dto, String col,
			String word, String nowPage) {

		HashMap map = new HashMap();
		map.put("no", dto.getNo());
		map.put("passwd", dto.getPasswd());

		boolean pflag = dao.passwd(map);

		if (pflag) {
			if (dao.update(dto)) {

				model.addAttribute("col", col);
				model.addAttribute("word", word);
				model.addAttribute("nowPage", nowPage);
				return "redirect:./list";
			} else {
				return "error";
			}
		}else{
			return "passwdError";
		}
	}



	@RequestMapping(value = "/images/update", method = RequestMethod.GET)
	public String update(Model model, int no, String col, String word, String nowPage) {

		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("no", no);
		model.addAttribute("dto", dao.read(no));

		return "/images/update";
	}

	@RequestMapping("/images/read")
	public String read(Model model, int no, String col, String word, String nowPage, HttpServletRequest request) {

		ImagesDTO dto = dao.read(no);
		dao.upViewcnt(no);
		Map map = dao.sumnail(no); 
		BigDecimal[] noArr = {((BigDecimal)map.get("PRE_NO2")), 
				((BigDecimal)map.get("PRE_NO1")),
				((BigDecimal)map.get("NO")),
				((BigDecimal)map.get("NEX_NO1")),
				((BigDecimal)map.get("NEX_NO2"))
		};

		String[] files = {
				((String)map.get("PRE_FILE2")),
				((String)map.get("PRE_FILE1")),
				((String)map.get("FILENAME")),
				((String)map.get("NEX_FILE1")),
				((String)map.get("NEX_FILE2"))
		
				};

		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");

		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("dto", dto);
		model.addAttribute("no", no);
		model.addAttribute("files", files);
		model.addAttribute("noArr", noArr);

		return "/images/read";
	}

	@RequestMapping(value = "/images/create", method = RequestMethod.POST)
	public String create(Model model, ImagesDTO dto, HttpServletRequest request) {
		String basePath = request.getRealPath("/image/storage");

		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";
		if (filesize > 0) {
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);

		boolean flag = dao.create(dto);
		if (flag) {
			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/images/create", method = RequestMethod.GET)
	public String create() {

		return "/images/create";
	}

	@RequestMapping("/images/list")
	public String list(HttpServletRequest request) {

		String col = Utility.checknull(request.getParameter("col"));
		String word = Utility.checknull(request.getParameter("word"));
		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		if (request.getParameter("nowPage") != null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		int recordPerPage = 5;

		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		ImagesDTO dto = new ImagesDTO();

		int total = dao.total(col, word);
		List<ImagesDTO> list = dao.list(map);
		Iterator<ImagesDTO> iter = list.iterator();
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);

		request.setAttribute("list", list);
		request.setAttribute("iter", iter);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);

		return "/images/list";
	}

}
