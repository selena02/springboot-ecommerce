package com.example.springbootprojectuni.service;

import com.example.springbootprojectuni.dto.CartItemResponse;
import com.example.springbootprojectuni.dto.CartResponse;
import com.example.springbootprojectuni.exception.CartNotFoundException;
import com.example.springbootprojectuni.exception.ProductNotFoundException;
import com.example.springbootprojectuni.exception.UserNotFoundException;
import com.example.springbootprojectuni.mapper.CartItemMapper;
import com.example.springbootprojectuni.model.Cart;
import com.example.springbootprojectuni.model.CartItem;
import com.example.springbootprojectuni.model.Product;
import com.example.springbootprojectuni.model.User;
import com.example.springbootprojectuni.repository.CartRepository;
import com.example.springbootprojectuni.repository.ProductRepository;
import com.example.springbootprojectuni.repository.UserRepository;
import com.example.springbootprojectuni.util.JwtUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Transactional
    public void addItemToCart(Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();

        Long userId = jwtUtil.getUserIdFromToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
    }

    @Transactional(readOnly = true)
    public CartResponse getAllItemsFromCart() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();

        Long userId = jwtUtil.getUserIdFromToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for the user." + userId));


        BigDecimal totalPrice = cart.getItems().stream()
                .map(item -> BigDecimal.valueOf(item.getProduct().getPrice())
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        List<CartItemResponse> itemResponses = cart.getItems().stream()
                .map(cartItemMapper::toResponse)
                .collect(Collectors.toList());

        // Create the response object
        CartResponse response = new CartResponse();
        response.setItems(itemResponses);
        response.setTotalPrice(totalPrice);

        return response;
    }

    @Transactional
    public void removeItemFromCart(Long productId, int quantityToRemove) {

        if (quantityToRemove <= 0) {
            throw new IllegalArgumentException("Quantity to remove must be greater than zero.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();

        Long userId = jwtUtil.getUserIdFromToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for the user." + userId));


        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found in the cart."));


        if (cartItem.getQuantity() > quantityToRemove) {
            cartItem.setQuantity(cartItem.getQuantity() - quantityToRemove);
        } else {
            cart.getItems().remove(cartItem);
        }

        cartRepository.save(cart);
    }

}
