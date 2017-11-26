package com.tecsup.integrador;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.integrador.api.ApiService;
import com.tecsup.integrador.api.ApiServiceGenerator;
import com.tecsup.integrador.model.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Controller
public class RegisterController {
	
	
	public String vista = "login";


	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@GetMapping("/register")
	public String preRegister() {
		
		 logger.info("Devolviendo vista Registro");
			
		return "register";
	}

//-----------------------------------------------------------------------------------------------------------
	@PostMapping("/register")
	public String login(final Model model,final HttpSession httpSession,
				  @RequestParam(value = "username") String username,
				  @RequestParam(value = "password") String password,
				  @RequestParam(value = "fullname") String fullname,
				  @RequestParam(value = "email") String email) throws InterruptedException 
	{								
	
   
    logger.info("Entrando al flujo de APi");
	//Iniciando el API
	 ApiService service = ApiServiceGenerator.createService(ApiService.class);
     Call<UserApi> call = service.createUsuario(username, password,fullname,email);
     logger.info("Culminó la creación del APi");			
	//Mensajes de error o bienvenida     
     
     call.enqueue(new Callback<UserApi>() 
     {    	
    	 public void onResponse(Call<UserApi> call, Response<UserApi> response) 
    	 {    		  
    	     try 
    	     {
    	         int statusCode = response.code();
    	         logger.info("HTTP status code: " + statusCode);    
    	         
    	         if (response.isSuccessful()) 
    	         {
    	             UserApi responseMessage = response.body();
    	             logger.info("responseMessage: " + responseMessage);
    	             logger.info("registro correcto");
    	             httpSession.setAttribute("usuario",responseMessage.getUsername());
    	             vista= "redirect:/admin/menu";
    	            
    	           
    	           
    	            
    	         } else 
    	         {
    	        	//progressDialog.dismiss();
    	        	 logger.info("Registro incorrecto");
    	        	 logger.info("onError: " + response.errorBody().string());
    	        	 model.addAttribute("message", "Registro incorrecto");
    	             vista="register";
    	         }
    	     }catch (Throwable t) 
    	     {
    	         		try 
    	         		{
    	         			logger.info("Error tipo T");
    	         			logger.info("onThrowable: " + t.toString());
    	         			logger.info("onThrowable: " + t.toString(), t);
    	        	
    	         			model.addAttribute("message", t.getMessage());
    	         			 vista= "register";   	        	           	             
    	         		} catch (Throwable x) 
    	         		{}
    	     }
    	     
 		}
         public void onFailure(Call<UserApi> call, Throwable t) 
         {	 
        	 logger.info("Error tipo onFailure, demora en responder");
        	 logger.info("onFailure: " + t.toString());
            
        	 model.addAttribute("message", t.getMessage());
        	 vista= "register";
	                  
         } 
         
         
      	
     });
     
     
     
     Thread.sleep(2000);
	logger.info("Retorna la vista");
 	logger.info(vista);
 	return vista;
	}
}