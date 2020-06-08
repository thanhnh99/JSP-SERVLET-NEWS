package com.shippingsystem.controller.adminApi;

import com.shippingsystem.models.request.AddNewStockRequest;
import com.shippingsystem.models.response.ResponseBaseModel;
import com.shippingsystem.models.response.ResponseListModel;
import com.shippingsystem.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/stock")
public class StockControllerAdmin {

    @Autowired
    private StockService stockService;

    @PostMapping("")
    public ResponseEntity addNewStock(@RequestBody AddNewStockRequest request) {
        ResponseBaseModel response = new ResponseBaseModel();
        response = stockService.addNewStock(request);

        if(response.getStatusCode().equals("200")) return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping
    public ResponseEntity getAllStock()
    {
        ResponseListModel response = stockService.getAll();
        if(response.getStatusCode().equals("200"))
        {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(203).body(response);
    }

    @GetMapping("{stock_id}/orders")
    public  ResponseEntity getAllOrderInStock(@PathVariable(name = "stock_id") String stockId)
    {
        ResponseListModel response = stockService.getAllOrderInStock(stockId);
        if(response.getStatusCode().equals("200"))
        {
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(203).body(response);
    }

}