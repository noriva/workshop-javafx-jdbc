package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class DepartmentService {
	
	private DepartamentoDao dao = DaoFactory.createDepartamentoDao();
	
	public List<Departamento> findAll(){
		
		return dao.findAll();
	}	
	
	
}
