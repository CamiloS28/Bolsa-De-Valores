package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.model.Billetera;
import co.edu.unbosque.repository.BilleteraRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BilleteraService {

    @Autowired
    private BilleteraRepository repository;

    public List<Billetera> getAll() {
        return repository.findAll();
    }

    public Optional<Billetera> getBilleteraById(int id) {
        return repository.findById(id);
    }

    public Billetera createBilletera(Billetera Billetera) {
        return repository.save(Billetera);
    }

    public Billetera updateBilletera(Billetera BilleteraDetails) {

        return repository.save(BilleteraDetails);
    }

    public void deleteBilletera(int id) {
        repository.deleteById(id);
    }

}
