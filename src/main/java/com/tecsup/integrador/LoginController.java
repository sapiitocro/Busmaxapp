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
public class LoginController {
	
	
	public String vista = "login";


	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String preLogin() {
		
		 logger.info("Devolviendo vista Login");
			
		return "login";
	}

//-----------------------------------------------------------------------------------------------------------
	@PostMapping("/login")
	public String login(final Model model,final HttpSession httpSession,
				  @RequestParam(value = "username") String username,
				  @RequestParam(value = "password") String password) throws InterruptedException 
	{								
	
   
    logger.info("Entrando al flujo de APi");
	//Iniciando el API
	 ApiService service = ApiServiceGenerator.createService(ApiService.class);
     Call<UserApi> call = service.loginUser(username, password);
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
    	             logger.info("Login correcto");
    	             httpSession.setAttribute("usuario",responseMessage.getUsername());
    	             httpSession.setAttribute("id",responseMessage.getId());
    	             vista= "redirect:/admin/user/editform/"+responseMessage.getId();
    	            
    	           
    	           
    	            
    	         } else 
    	         {
    	        	//progressDialog.dismiss();
    	        	 logger.info("Login incorrecto");
    	        	 logger.info("onError: " + response.errorBody().string());
    	        	 model.addAttribute("message", "Usuario y clave incorrectos");
    	             vista="login";
    	         }
    	     }catch (Throwable t) 
    	     {
    	         		try 
    	         		{
    	         			logger.info("Error tipo T");
    	         			logger.info("onThrowable: " + t.toString());
    	         			logger.info("onThrowable: " + t.toString(), t);
    	        	
    	         			model.addAttribute("message", t.getMessage());
    	         			 vista= "login";   	        	           	             
    	         		} catch (Throwable x) 
    	         		{}
    	     }
    	     
 		}
         public void onFailure(Call<UserApi> call, Throwable t) 
         {	 
        	 logger.info("Error tipo onFailure, demora en responder");
        	 logger.info("onFailure: " + t.toString());
            
        	 model.addAttribute("message", t.getMessage());
        	 vista= "login";
	                  
         } 
         
         
      	
     });
     
     
     
     Thread.sleep(2000);
	logger.info("Retorna la vista");
 	logger.info(vista);
 	return vista;
	}
}