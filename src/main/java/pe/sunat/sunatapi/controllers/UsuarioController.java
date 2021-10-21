package pe.sunat.sunatapi.controllers;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.sunat.sunatapi.models.Usuario;
import pe.sunat.sunatapi.repositories.FacturaRepository;
import pe.sunat.sunatapi.repositories.UsuarioRepository;

@Controller
@RequestMapping(value = "app")
public class UsuarioController {
    private static final String LOGIN_INDEX = "login";
    private static final String REGISTER_INDEX = "register";
    private static final String REGISTER_SUCCESS = "register_success";
    private static String MODEL_CONTACT="factura";
    private static String MODEL_MESSAGE="mensaje";
    private final FacturaRepository facturaData;
    private final UsuarioRepository usuariosData;
    
    public UsuarioController(FacturaRepository facturaData, UsuarioRepository usuariosData){
        this.facturaData = facturaData;
        this.usuariosData = usuariosData;
    }      

    @GetMapping("/login")
    public String getLogin(Model model){
        return LOGIN_INDEX;
    }
    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("usuario", new Usuario());
        return REGISTER_INDEX;
    }
    @PostMapping("/process_register")
    public String postRegister(Usuario usuario){
        usuariosData.save(usuario);
        return REGISTER_SUCCESS;
    }
    @PostMapping("/login")
    public String loginSubmitForm(Model model,@Valid Usuario objUser, HttpServletRequest request, BindingResult result )
    {
        String page=LOGIN_INDEX;
        model.addAttribute(MODEL_CONTACT, new Usuario());
        if(result.hasFieldErrors()) {
            model.addAttribute(MODEL_MESSAGE, "No se ha podido loguear");
        }else{
            Optional<Usuario> userDB = this.usuariosData.findById(objUser.getUserID());
            if(!userDB.isPresent()){
                model.addAttribute(MODEL_MESSAGE, "DNI no existe");
            }
        }
        return page;             
    }
}
