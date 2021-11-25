package com.raspi.retail.backend.model.dtos;

/**
 * Data Transfer Objects (DTOs)
 *
 * <p>
 *     These are objects that are used to hold data gathered from DAOs. They can
 *     also be read app-wide, and can be accessed as such, but only through the
 *     DAOs.
 * </p>
 */
public interface DTO extends Cloneable {

    Object clone() throws CloneNotSupportedException;

}
