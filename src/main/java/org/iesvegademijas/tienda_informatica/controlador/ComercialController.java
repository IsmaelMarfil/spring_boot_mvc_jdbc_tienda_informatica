package org.iesvegademijas.tienda_informatica.controlador;

import java.util.List;

import jakarta.validation.Valid;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.iesvegademijas.tienda_informatica.servicio.FabricanteService;
import org.iesvegademijas.tienda_informatica.servicio.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//Para utilizar una base de url, pero se visualiza mejor con toda la ruta en los métodos
//@RequestMapping("/comerciales")
public class ComercialController {

    @Autowired
    private ComercialService comercialService;
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/comerciales")
    public String listar(Model model) {

        List<Comercial> listAllCom =  comercialService.listAll();
        model.addAttribute("listaComerciales", listAllCom);

        return "comerciales";

    }

    @GetMapping("/comerciales/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {
        List<Pedido> pedidos = pedidoService.devolverListaPedidos(id);
        Double media = pedidoService.media(id);
        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);
        model.addAttribute("listaPedidos", pedidos);
        model.addAttribute("media", media);

        return "detalle-comercial";

    }

    @GetMapping("/comerciales/crear")
    public String crear(Model model) {

        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "crear-comercial";

    }

    @PostMapping("/comerciales/crear")
    public String submitCrear(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("comercial", comercial);
            return "crear-comercial";
        }
        comercialService.newComercial(comercial);
        return "redirect:/comerciales?newComercialID=" + comercial.getId();

    }

    @GetMapping("/comerciales/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        return "editar-comercial";

    }


    @PostMapping("/comerciales/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("comercial", comercial);
            return "editar-comercial";
        }
        comercialService.replaceComercial(comercial);
        return "redirect:/comerciales?newComercialID=" + comercial.getId();

    }

    @PostMapping("/comerciales/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        comercialService.deleteComercial(id);

        return new RedirectView("/comerciales");
    }


}
