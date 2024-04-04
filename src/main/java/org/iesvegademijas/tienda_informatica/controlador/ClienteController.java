package org.iesvegademijas.tienda_informatica.controlador;

import java.util.List;

import jakarta.validation.Valid;
import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ClienteDAOImpl;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.iesvegademijas.tienda_informatica.servicio.FabricanteService;
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
//@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/clientes")
    public String listar(Model model) {

        List<Cliente> listAllCli =  clienteService.listAll();
        model.addAttribute("listaClientes", listAllCli);

        return "clientes";

    }

    @GetMapping("/clientes/{id}")
    public String detalle(Model model, @PathVariable Integer id ) {

        Cliente cliente = clienteService.one(id);

        model.addAttribute("cliente", cliente);

        List<Comercial> comerciales = clienteService.listadoComerciales(id);

        // Mapear la lista de comerciales a una lista de ComercialDTO
        int conteotrimestre =clienteService.calcularConteoPedidosUltimoTrimestre(cliente);

        model.addAttribute("conteoUltimoTrimestre", conteotrimestre);

        int conteoSemestre=clienteService.calcularConteoPedidosUltimoSemestre(cliente);

        model.addAttribute("conteoUltimoSemestre", conteoSemestre);

        int conteoAnio =clienteService.calcularConteoPedidosUltimoAnio(cliente);

        model.addAttribute("conteoUltimoAnio", conteoAnio);
        int conteoLustro =clienteService.calcularConteoPedidosUltimoLustro(cliente);

        model.addAttribute("conteoUltimoLustro", conteoLustro);

        // Agregar la lista de comerciales al modelo
        model.addAttribute("comerciales", comerciales);

        return "detalle-cliente";


    }

    @GetMapping("/clientes/crear")
    public String crear(Model model) {

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "crear-cliente";

    }

    @PostMapping("/clientes/crear")
    public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {

      if(bindingResult.hasErrors()){
          model.addAttribute("cliente", cliente);
          return "crear-cliente";
      }
        clienteService.newCliente(cliente);
      return "redirect:/clientes?newClienteID=" + cliente.getId();

    }

    @GetMapping("/clientes/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

        Cliente cliente = clienteService.one(id);
        model.addAttribute("cliente", cliente);

        return "editar-cliente";

    }


    @PostMapping("/clientes/editar/{id}")
    public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente,BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("cliente", cliente);
            return "editar-cliente";
        }
        clienteService.replaceCliente(cliente);
        return "redirect:/clientes?newClienteID=" + cliente.getId();

    }


    @PostMapping("/clientes/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {

        clienteService.deleteCliente(id);

        return new RedirectView("/clientes");
    }


}
