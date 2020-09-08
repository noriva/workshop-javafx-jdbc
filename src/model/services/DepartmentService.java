package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Departamento;

public class DepartmentService {

	public List<Departamento> findAll(){
		
		List<Departamento> lista = new ArrayList<>();
		lista.add(new Departamento(1, "Books"));
		lista.add(new Departamento(2, "Computers"));
		lista.add(new Departamento(3, "Eletronics"));
		
		return lista;
	}	
	
	
}
