package net.qiqb.usr.custom.client.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.qiqb.usr.custom.client.types.CustomerId;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Setter
public class CreatedCustomEvent implements Serializable {

    private CustomerId customerId;

    public CreatedCustomEvent(CustomerId customerId) {
        this.customerId = customerId;
    }
}
