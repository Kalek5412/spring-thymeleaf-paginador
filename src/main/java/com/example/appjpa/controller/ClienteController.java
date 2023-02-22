package com.example.appjpa.controller;


import com.example.appjpa.entity.Cliente;
import com.example.appjpa.service.ClienteService;
import com.example.appjpa.util.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;


@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public String  listarClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model){
        Pageable pageReq= PageRequest.of(page,4);
        Page<Cliente> clientes=clienteService.findAll(pageReq);

        PageRender<Cliente> pageRender=new PageRender<>("/listar",clientes);
        model.addAttribute("titulo","Listado de clientes");
        model.addAttribute("clientes",clientes);
        model.addAttribute("page",pageRender);
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String formulario(Map<String,Object> model){
        Cliente cliente =new Cliente();
        model.put("cliente",cliente);
        model.put("titulo","formulario de clientes");
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash){
        if(result.hasErrors()){
            model.addAttribute("titulo","Formulario de cliente");
            return "form";
        }
        clienteService.guardar(cliente);
        flash.addFlashAttribute("success","cliente creado con exito!");
        return "redirect:listar";
    }
    @RequestMapping(value="/form/{id}")
    public String editar(@PathVariable Integer id,Map<String,Object> model){
        Optional<Cliente> cliente=clienteService.buscarPorId(id);
        if(!cliente.isPresent()){
            return "redirect:/listar";
        }
        model.put("cliente",cliente);
        model.put("titulo","Editar cliente");
        return "form";
    }
    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash){
        if(clienteService.buscarPorId(id).isPresent()){
            clienteService.eliminar(id);
            flash.addFlashAttribute("success","cliente eliminado con exito!");
        }
        return "redirect:/listar";
    }






}
