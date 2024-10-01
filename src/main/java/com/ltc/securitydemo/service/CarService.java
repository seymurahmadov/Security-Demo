package com.ltc.securitydemo.service;

import com.ltc.securitydemo.dto.request.CarRequestDto;
import com.ltc.securitydemo.dto.response.CarResponseDto;
import com.ltc.securitydemo.entity.CarEntity;
import com.ltc.securitydemo.entity.OwnerEntity;
import com.ltc.securitydemo.repository.CarRepository;
import com.ltc.securitydemo.repository.OwnerRepository;
import com.ltc.securitydemo.security.UserEntity;
import com.ltc.securitydemo.security.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final OwnerRepository ownerRepository;
    private final UserRepo userRepo;

    public CarService(CarRepository carRepository, ModelMapper modelMapper, OwnerRepository ownerRepository, UserRepo userRepo) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.ownerRepository = ownerRepository;
        this.userRepo = userRepo;
    }


    public List<CarResponseDto> getAll() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Carin get All metodu cagirdim");
        List<CarEntity> all = carRepository.findAll();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));

        List<CarResponseDto> list = all.stream()
                .filter(user -> user.getUserEntity().getEmail().equals(authentication.getName()) || isAdmin)
                .map(item -> modelMapper.map(item, CarResponseDto.class))
                .toList();

        log.info("Carin get All metodu cagrildi. her sey yaxsidi");

        return list;



    }

    public CarResponseDto findById(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElseThrow();
        CarResponseDto sevinc =  modelMapper.map(carEntity, CarResponseDto.class);
        return sevinc;
    }








    public String create( CarRequestDto carDto) {

        // Kullanıcıyı kimlik doğrulamasından alıyoruz
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("User: " + authentication.getName());
            System.out.println("Authorities: " + authentication.getAuthorities());
        } else {
            System.out.println("No authentication found.");
        }

        // ModelMapper ile manuel eşleme yapıyoruz
        modelMapper.typeMap(CarRequestDto.class, CarEntity.class)
                .addMappings(mapper -> {
                    mapper.skip(CarEntity::setId); // ID'yi atla
//                    mapper.skip(CarEntity::setOwnerEntity); // OwnerEntity'yi manuel atayacağız
//                    mapper.skip(CarEntity::setUserEntity);  // UserEntity'yi manuel atayacağız
                });

        // DTO'dan entity'e kalan alanları eşliyoruz
        CarEntity car = modelMapper.map(carDto, CarEntity.class);

        // Owner ve User entity'lerini manuel olarak alıyoruz
//        OwnerEntity ownerEntity = ownerRepository.findById(carDto.getOwnerId())
//                .orElseThrow(() -> new RuntimeException("Owner not found"));
        UserEntity userEntity = userRepo.findById(carDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Elde ettiğimiz Owner ve User entity'lerini CarEntity'ye set ediyoruz
//        car.setOwnerEntity(ownerEntity);
        car.setUserEntity(userEntity);

        // Veritabanına kaydediyoruz
        carRepository.save(car);

        return "Car added successfully";
    }







    public String delete( Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Araç nesnesini veritabanından bul
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id " + id));

        // Kullanıcının e-posta adresini al
        String username = authentication.getName();

        // Debug log
        System.out.println("Authenticated User: " + username);
        System.out.println("Car Owner Email: " + carEntity.getUserEntity().getEmail());

//         Kullanıcının aracı oluşturup oluşturmadığını kontrol et
        if (username.equals(carEntity.getUserEntity().getEmail())) {
            carRepository.delete(carEntity);
            return "Car deleted successfully";
        } else {
            System.out.println("Bura dusdu");
            throw new EntityNotFoundException("You do not have permission to delete this car.");
        }



    }

    public String update( Long id, CarRequestDto carDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CarEntity carEntity = carRepository.findById(id).orElseThrow();

        if (authentication != null && authentication.getName().equals(carEntity.getUserEntity().getEmail())) {
            System.out.println("User: " + authentication.getName());

            modelMapper.typeMap(CarRequestDto.class, CarEntity.class)
                    .addMappings(mapper -> mapper.skip(CarEntity::setId));

            modelMapper.map(carDto, carEntity);

            carRepository.save(carEntity);
            return "Car updated successfully";

        }else {
            throw new AccessDeniedException("You do not have permission to delete this car.");
        }



    }



    public List<CarResponseDto> getAllByName(String carName){
        List<CarEntity> allByCarName = carRepository.findAllByCarName(carName);

        List<CarResponseDto> list = allByCarName.stream()
                .map(item -> modelMapper.map(item, CarResponseDto.class))
                .toList();


        return list;
    }




    @Scheduled(cron = "0 33 13 * * ?")
    public void sayCarName() throws InterruptedException {
        System.out.println("Mercedes " + LocalTime.now());
    }
}
