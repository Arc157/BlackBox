package top.niunaijun.blackbox.fake.frameworks;

import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.app.BActivityThread;
import top.niunaijun.blackbox.core.system.ServiceManager;
import top.niunaijun.blackbox.core.system.location.IBLocationManagerService;
import top.niunaijun.blackbox.entity.location.BCell;
import top.niunaijun.blackbox.entity.location.BLocation;

/**
 * Created by BlackBoxing on 3/8/22.
 **/
public class BLocationManager {
    private static final BLocationManager sLocationManager = new BLocationManager();
    private IBLocationManagerService mService;

    public static final int CLOSE_MODE = 0;
    public static final int GLOBAL_MODE = 1;
    public static final int OWN_MODE = 2;

    public static BLocationManager get() {
        return sLocationManager;
    }

    public static boolean isFakeLocationEnable() {
        return get().getPattern(BActivityThread.getUserId(), BActivityThread.getAppPackageName()) != CLOSE_MODE;
    }

    public void setPattern(int userId, String pkg, int pattern) {
        try {
            getService().setPattern(userId, pkg, pattern);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getPattern(int userId, String pkg) {
        try {
            return getService().getPattern(userId, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return CLOSE_MODE;
    }

    public void setCell(int userId, String pkg, BCell cell) {
        try {
            getService().setCell(userId, pkg, cell);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setAllCell(int userId, String pkg, List<BCell> cells) {
        try {
            getService().setAllCell(userId, pkg, cells);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setSurroundingCell(int userId, String pkg, List<BCell> cells) {
        try {
            getService().setSurroundingCell(userId, pkg, cells);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setGlobalCell(BCell cell) {
        try {
            getService().setGlobalCell(cell);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setGlobalAllCell(List<BCell> cells) {
        try {
            getService().setGlobalAllCell(cells);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setGlobalSurroundingCell(List<BCell> cells) {
        try {
            getService().setGlobalSurroundingCell(cells);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public BCell getCell(int userId, String pkg) {
        try {
            return getService().getCell(userId, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BCell> getAllCell(int userId, String pkg) {
        try {
            return getService().getAllCell(userId, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void setLocation(int userId, String pkg, BLocation location) {
        try {
            getService().setLocation(userId, pkg, location);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public BLocation getLocation(int userId, String pkg) {
        try {
            return getService().getLocation(userId, pkg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGlobalLocation(BLocation location) {
        try {
            getService().setGlobalLocation(location);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public BLocation getGlobalLocation() {
        try {
            return getService().getGlobalLocation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


    private IBLocationManagerService getService() {
        if (mService != null && mService.asBinder().isBinderAlive()) {
            return mService;
        }
        mService = IBLocationManagerService.Stub.asInterface(BlackBoxCore.get().getService(ServiceManager.LOCATION_MANAGER));
        return getService();
    }
}
