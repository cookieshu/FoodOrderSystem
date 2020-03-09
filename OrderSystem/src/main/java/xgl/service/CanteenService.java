package xgl.service;

import xgl.pojo.Canteen;

import java.util.List;

public interface CanteenService {
        void add(Canteen canteen);
        void delete(int id);
        void update(Canteen canteen);
        Canteen get(int id);
        List<Canteen>list();
}
