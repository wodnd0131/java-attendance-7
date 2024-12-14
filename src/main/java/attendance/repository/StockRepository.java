package attendance.repository;

import java.util.List;
import java.util.stream.Collectors;

import attendance.domain.stock.Stock;

public class StockRepository extends FileRepository<Stock> {
    private static final String RESOURCE_STOCK_PATH = "/products.md";

    public StockRepository() {
        super(RESOURCE_STOCK_PATH);
    }

    @Override
    public List<Stock> findAll() {
        List<String> lines = readLines();
        return lines.stream()
            .map(this::parseProduct)
            .collect(Collectors.toList());
    }

    private Stock parseProduct(String line) {
        String[] parts = line.split(",");
        return new Stock();
    }
}

