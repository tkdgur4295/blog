package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.team.TeamDTO;
import spring.model.team.TeamDAO;
import spring.utility.blog.Utility;

@Controller
public class TeamController {
	@Autowired
	private TeamDAO dao;

	@RequestMapping("/team/delete")
	public String delete(int no, String oldfile, HttpServletRequest request, String col, String word, String nowPage,
			Model model) {

		String basePath = request.getRealPath("/team/storage");
		boolean flag = dao.delete(no);
		if (flag) {
			if (oldfile != null && !oldfile.equals("member.jpg")) {
				Utility.deleteFile(basePath, oldfile);
			}
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/team/update", method = RequestMethod.POST)
	public String update(TeamDTO dto, HttpServletRequest request, String oldfile, String col, String word,
			String nowPage, Model model) {
		String basePath = request.getRealPath("/team/storage");
		int filesize = (int) dto.getFileMF().getSize();
		String filename = "member.jpg";
		if (filesize > 0) {
			if (oldfile != null && !oldfile.equals("member.jpg")) {
				Utility.deleteFile(basePath, oldfile);
			}
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		} else if (filename != null) {
			filename = oldfile;
		}

		dto.setFilename(filename);

		boolean flag = dao.update(dto);

		if (flag) {
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		} else {
			return "error";

		}

	}

	@RequestMapping(value = "/team/update", method = RequestMethod.GET)
	public String update(int no, Model model) {

		model.addAttribute("dto", dao.read(no));

		return "/team/update";
	}

	@RequestMapping("/team/read")
	public String read(int no, Model model) {

		model.addAttribute("dto", dao.read(no));

		return "/team/read";
	}

	@RequestMapping(value = "/team/create", method = RequestMethod.POST)
	public String create(TeamDTO dto, HttpServletRequest request) {

		String basePath = request.getRealPath("/team/storage");
		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";
		if (filesize > 0) {
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);
		if (dao.create(dto)) {
			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/team/create", method = RequestMethod.GET)
	public String create() {

		return "/team/create";
	}

	@RequestMapping("/team/list")
	public String List(HttpServletRequest request) {

		// 검색관련
		String col = Utility.checknull(request.getParameter("col"));
		String word = Utility.checknull(request.getParameter("word"));
		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1; // 현재페이지 ,변경가능
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 5;// 한페이지당 보여줄 레코드 갯수

		int total = dao.total(col, word);
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<TeamDTO> list = dao.list(map);
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);

		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);

		return "/team/list";
	}

}
