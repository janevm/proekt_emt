package mk.ukim.finki.emt.healthy_food_shop.productcatalog;

//import lombok.var;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.Manufacturer;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.ManufacturerId;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.Product;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.repository.ManufacturerRepository;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location.Address;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
class DataGenerator {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    DataGenerator(ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        if (manufacturerRepository.findAll().size()==0 && productRepository.findAll().size()==0){
            var manufacturers = new ArrayList<Manufacturer>();
            manufacturers.add(createManufacturer(new ManufacturerId("1"), "Bioherbal",
                    new Address("Envoy Ct.", "2056", "Clearwater", "USA", "33764")));
            manufacturers.add(createManufacturer(new ManufacturerId("2"), "Galafarm",
                    new Address("St 51, Stopanski Dvor", "23", "Skopje", "Macedonia", "1000")));
            manufacturers.add(createManufacturer(new ManufacturerId("3"), "Probotanic",
                    new Address("Vučićev prolaz", "21a", "Beograd", "Serbia", "11050")));
            manufacturerRepository.saveAll(manufacturers);

            var products = new ArrayList<Product>();
            products.add(createProduct(new ProductId("1"),"Soda bez aluminijum", new Money(630d, Currency.MKD),
                    15, "Hrana", "", manufacturers.get(1)));
            products.add(createProduct(new ProductId("2"),"Origanol strong", new Money(1200d, Currency.MKD),
                    10, "Lek", "", manufacturers.get(2)));
            products.add(createProduct(new ProductId("3"),"Jekogal ribino maslo",  new Money(450d, Currency.MKD),
                    20, "Dodatok na ishrana", "", manufacturers.get(1)));
            productRepository.saveAll(products);
        }

    }

    private Product createProduct(ProductId productId, String name, Money price, int qnt, String category, String imgUrl, Manufacturer manufacturer) {
        var product = new Product(productId, name, price, qnt, category, imgUrl, manufacturer);
        return product;
    }

    private Manufacturer createManufacturer(ManufacturerId manufacturerId, String name, Address address) {
        var manufacturer = new Manufacturer(manufacturerId, name, address);
        return manufacturer;
    }
}
