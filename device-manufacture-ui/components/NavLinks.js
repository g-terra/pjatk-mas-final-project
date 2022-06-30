import { Category, Dashboard, Inventory, Logout, ManageAccounts, Search } from "@mui/icons-material";

export const navLinks = [
    {
        name: "Dashboard",
        path: "/",
        icon: <Dashboard />

    },
    {
        name: "Employee Management",
        path: "/",
        icon: <ManageAccounts />

    },
    {
        name: "Orders",
        path: "/orders/search",
        icon: <Inventory />
    },
    {
        name: "Device Types",
        path: "/device-type/search",
        icon: <Search />
    },
    {
        name: "Products",
        path: "/products/search",
        icon: <Category />
    },
    {
        name: "Logout",
        path: "/logout",
        icon: <Logout />
    }
    
];