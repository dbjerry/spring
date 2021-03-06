package kr.or.ddit.mvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.file.util.FileUtil;
import kr.or.ddit.hello.HelloControllerTest;
import kr.or.ddit.test.ControllerTestConfig;

public class MvcControllerTest extends ControllerTestConfig{

	Logger logger = (Logger) LoggerFactory.getLogger(HelloControllerTest.class);
	
	@Test
	public void mvcViewTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/view")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		@SuppressWarnings("unchecked")
		List<String> minions = (List<String>) mav.getModel().get("minions");
		
		/***Then***/
		assertEquals("mvc/view", viewName);
		
		//model객체에서 minions 속성을 받아 사이즈가 3인지 테스트
		//(controller에서 한 건 추가)
		assertEquals(4, minions.size());
	}

	
	@Test
	public void mvcView2Test() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/view2")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName2 = mav.getViewName();
		@SuppressWarnings("unchecked")
		List<String> minions = (List<String>) mav.getModel().get("minions");
		
		/***Then***/
		assertEquals("mvc/view", viewName2);
		
		//model객체에서 minions 속성을 받아 사이즈가 3인지 테스트
		assertEquals(3, minions.size());
	}
	
	
	@Test
	public void fileuploadViewTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/mvc/fileupload")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("mvc/fileuploadView", mav.getViewName());
	}
	
	
	//테스트코드를 운영코드보다 먼저 작성하는 방법
	//Test Driven Development (TDD)
	@Test
	public void getFileExtensionTest() {
		/***Given***/
		String fileName = "sally.png";

		/***When***/
		String ext = FileUtil.getFileExtension(fileName);

		/***Then***/
		assertEquals(".png", ext);

	}
	
}

