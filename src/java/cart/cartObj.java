/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP
 */
public class cartObj {
    private Map<String, Integer> items;
    public Map<String,Integer> getItems(){
        return items;
    }
    public boolean addItemToCart(String id, int quantity){
        boolean result = false;
        //1. Check data valid
        if(id == null){
            return result;
        }
        if(id.trim().isEmpty()){
            return result;
        }
        if(quantity <= 0){
            return result;
        }
        //2. Check exist items
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //3. Check exist item
        if(this.items.containsKey(id)){
            int currentQuantity = (int)this.items.get(id);
            quantity = quantity + currentQuantity;
        } //end item has existed
        //4. Update cart items
        this.items.put(id, (Integer)quantity);
        result = true;
        return result;
    }
}
