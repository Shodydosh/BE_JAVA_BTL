package com.javaBTL.BE_JAVA_BTL.service.impl;

import com.javaBTL.BE_JAVA_BTL.model.Shipment;
import com.javaBTL.BE_JAVA_BTL.model.ShipmentStatus;
import com.javaBTL.BE_JAVA_BTL.repository.ShipmentRepository;
import com.javaBTL.BE_JAVA_BTL.service.ShipmentService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment updateShipment(UUID id, Shipment shipment) {
        Shipment existingShipment = getShipmentById(id);
        // Update fields
        existingShipment.setTrackingNumber(shipment.getTrackingNumber());
        existingShipment.setRecipientName(shipment.getRecipientName());
        existingShipment.setRecipientPhone(shipment.getRecipientPhone());
        existingShipment.setShippingAddress(shipment.getShippingAddress());
        existingShipment.setNotes(shipment.getNotes());
        existingShipment.setShippingFee(shipment.getShippingFee());
        existingShipment.setUpdatedAt(LocalDateTime.now());
        
        return shipmentRepository.save(existingShipment);
    }

    @Override
    public void deleteShipment(UUID id) {
        shipmentRepository.deleteById(id);
    }

    @Override
    public Shipment getShipmentById(UUID id) {
        return shipmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    @Override
    public List<Shipment> getShipmentsByOrderId(UUID orderId) {
        return shipmentRepository.findByOrderId(orderId);
    }

    @Override
    public Shipment updateShipmentStatus(UUID id, ShipmentStatus status) {
        Shipment shipment = getShipmentById(id);
        shipment.setStatus(status);
        shipment.setUpdatedAt(LocalDateTime.now());
        return shipmentRepository.save(shipment);
    }
}
