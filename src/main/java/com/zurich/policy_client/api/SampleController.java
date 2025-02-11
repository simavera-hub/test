package com.zurich.policy_client.api;

//import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*import com.zurich.bts.timetracking.api.config.Enviroment;
import com.zurich.bts.timetracking.api.response.ResponseUserInfoBean;
import com.zurich.bts.timetracking.service.SampleService;
import com.zurich.bts.timetracking.util.ResponseUtil;
*/
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/sample") 
public class SampleController {
 
	/*@Autowired
	private ResponseUtil responseUtil;
	@Autowired
	private Enviroment env;
	@Autowired
	private SampleService sampleService;

	private String OK_MESSAGE = "Operacion satisfactoria";
   @GetMapping(value = "/user")
	public ResponseEntity getConsultaTramites(@RequestHeader("username") String user,
			@RequestParam(name = "userId", required = false) Integer userId,			
			@RequestParam(name = "grupo", required = false) String grupo) throws ValidationException {

		log.info("userId :" + userId + " " +env.getSampleVar1());


		try {
			ResponseUserInfoBean bean = new ResponseUserInfoBean();
            return this.responseUtil
                    .successResponse(sampleService.getUserInfoByUserId(userId), OK_MESSAGE);
		} catch (ValidationException ex) {
			log.error("Ocurrio un error", ex);
			return this.responseUtil.businessErrorResponse(ex.getMessage());
		}
	}
*/
}
