package br.com.dias.cars.service;

import br.com.dias.cars.exception.RecursoNaoEncontradoException;
import br.com.dias.cars.model.Carro;
import br.com.dias.cars.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {
    private final CarroRepository carroRepository;

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado com id: " + id));
    }

    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deletar(Long id) {
        carroRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Carro não encontrado com id: " + id));
        carroRepository.deleteById(id);
    }

    public Carro atualizar(Long id, Carro carroAtualizado) {
        if (!carroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Carro não encontrado com id: " + id);
        }
        carroAtualizado.setId(id);
        return carroRepository.save(carroAtualizado);
    }
}