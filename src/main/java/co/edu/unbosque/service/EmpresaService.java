package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Empresa;
import co.edu.unbosque.model.Empresa;
import co.edu.unbosque.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	public List<Empresa> getAll() {
		return repository.findAll();
	}

	public Optional<Empresa> getEmpresaById(int id) {
		return repository.findById(id);
	}

	public Empresa createEmpresa(Empresa Empresa) {
		return repository.save(Empresa);
	}

	public Empresa updateEmpresa(Empresa EmpresaDetails) {

		return repository.save(EmpresaDetails);
	}

	public void deleteEmpresa(int id) {
		repository.deleteById(id);
	}

//	public Optional<Empresa> findById(Integer id);
//
//    public List<Empresa> findAll();
//
//    public Optional<Empresa> findByNombre(String nombre);
}
