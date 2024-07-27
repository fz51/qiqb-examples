package net.qiqb.usr.custom.client.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.qiqb.usr.custom.client.types.CustomerId;
@Getter
@NoArgsConstructor
@Setter
public class CreatedCustomEvent {

    private CustomerId customerId;

    public CreatedCustomEvent(CustomerId customerId) {
        this.customerId = customerId;
    }
}
