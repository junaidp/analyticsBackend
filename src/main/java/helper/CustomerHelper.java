package helper;

import com.google.gson.Gson;
import model.Customer;
import model.ErrorCustom;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import repository.CustomerRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CustomerHelper {

    @Autowired
     CustomerRepository repository;
    @Autowired
    MongoOperations mongoOperation;
    static String SHEET = "Control sheet";
    private Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(Customer.class);

    public String saveAll(MultipartFile file) {
        String response = "Customer record has been uploaded";
        try {
            List<Customer> customerList = excelToCustomersList(file.getInputStream());
            for (Customer customerEnglish : customerList ) {
                response = saveCustomer(customerEnglish);
            }
        } catch (IOException e) {
            response = "fail to store excel data: " + e.getMessage();
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
        return gson.toJson(response);
    }

    //reading from uploaded excel file
    public static List<Customer> excelToCustomersList(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Customer> customerList = new ArrayList<Customer>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Customer customer = new Customer();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            if( (int)currentCell.getNumericCellValue() >= 0) {
                                int code = (int) currentCell.getNumericCellValue();
                                customer.setJcCode(String.valueOf(code));
                                break;
                            }else
                                return customerList;

                        case 1:
                            if(!currentCell.getStringCellValue().isEmpty()) {
                                customer.setDate(currentCell.getStringCellValue());
                                break;
                            }else
                                return customerList;

                        case 2:
                            customer.setClDate(currentCell.getStringCellValue());
                            break;

                        case 3:
                            customer.setType(currentCell.getStringCellValue());
                            break;

                        case 4:
                            customer.setCustDescription(currentCell.getStringCellValue());
                            break;

                        case 5:
                            customer.setService(currentCell.getStringCellValue());
                            break;

                        case 6:
                            int amt = (int)currentCell.getNumericCellValue();
                            customer.setAmt(String.valueOf(amt));
                            break;

                        case 7:
                            int amtWar = (int)currentCell.getNumericCellValue();
                            customer.setAmtWarranty(String.valueOf(amtWar));
                            break;

                        case 8:
                            int taxAmount = (int)currentCell.getNumericCellValue();
                            customer.setTaxAmount(String.valueOf(taxAmount));
                            break;

                        case 9:
                            int totalAmount = (int)currentCell.getNumericCellValue();
                            customer.setTotalAmount(String.valueOf(totalAmount));
                            break;

                        case 10:
                            customer.setDay(currentCell.getStringCellValue());
                            break;

                        case 11:
                            customer.setDay(currentCell.getStringCellValue());
                            break;

                        case 12:
                            customer.setMonth(currentCell.getStringCellValue());
                            break;

                        case 13:
                            customer.setYear(currentCell.getStringCellValue());
                            break;

                        case 14:
                            customer.setDt(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }
                    cellIdx++;
                }
                customerList.add(customer);
                System.out.println("Last added customer "+customer.getCustDescription()+" | "+customer.getJcCode());
            }
            workbook.close();
            return customerList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public String saveCustomer(Customer customer) {
        ErrorCustom error = new ErrorCustom();
        String jsonError;
            try {
                repository.save(customer);
                 return "Customer Saved";
            } catch (Exception ex) {
                logger.info("Exception in saveCustomerEnglish "+ex.getMessage());
                error.setErrorStatus("Error");
                error.setError(ex.getMessage());
                jsonError = gson.toJson(error);
                return jsonError;
            }
    }

}
