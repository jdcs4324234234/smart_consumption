package com.unicauca.smart_consumption.application.service.product;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.unicauca.smart_consumption.domain.common.ResponseDto;
import com.unicauca.smart_consumption.domain.constant.MessagesConstant;
import com.unicauca.smart_consumption.domain.product.Product;
import com.unicauca.smart_consumption.domain.product.ports.in.IProductCommandService;
import com.unicauca.smart_consumption.domain.product.ports.out.IProductCommandRepository;
import com.unicauca.smart_consumption.infrastructure.exception.BusinessRuleException;
import com.unicauca.smart_consumption.infrastructure.messages.MessageLoader;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements IProductCommandService{

    private final IProductCommandRepository productCommandRepository;

    @Override
    public ResponseDto<Product> createProduct(Product product) {

        Product productNew = productCommandRepository.createProduct(product);

        return new ResponseDto<>(HttpStatus.CREATED.value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM001), productNew);
   
    }

    @Override
    public ResponseDto<Product> updateProduct(String id, Product product) {
        Product productUpdated = productCommandRepository.updateProduct(id, product);
        return new ResponseDto<>(HttpStatus.OK.value(),
            MessageLoader.getInstance().getMessage(MessagesConstant.IM003), productUpdated);    
    }

    @Override
    public ResponseDto<Void> deleteProduct(String id) {
        if (productCommandRepository.findProductById(id).isEmpty()) {
            productCommandRepository.deleteProduct(id);
            return new ResponseDto<>(HttpStatus.NO_CONTENT.value(),
                MessageLoader.getInstance().getMessage(MessagesConstant.IM004));
        } else {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstant.EM002,
                MessageLoader.getInstance().getMessage(MessagesConstant.EM002, id));
        }
    }
    
}
