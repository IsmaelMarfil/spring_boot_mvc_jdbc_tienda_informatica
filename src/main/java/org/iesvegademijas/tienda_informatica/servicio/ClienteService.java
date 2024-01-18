package org.iesvegademijas.tienda_informatica.servicio;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.dao.FabricanteDAO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public List<Cliente> listAll() {

        return clienteDAO.getAll();

    }

    public Cliente one(Integer id) {
        Optional<Cliente> optCli= clienteDAO.find(id);
        if (optCli.isPresent())
            return optCli.get();
        else
            return null;
    }

    public void newCliente(Cliente cliente) {

        clienteDAO.create(cliente);

    }

    public void replaceCliente(Cliente cliente) {

        clienteDAO.update(cliente);

    }

    public void deleteCliente(int id) {

        clienteDAO.delete(id);

    }

}
