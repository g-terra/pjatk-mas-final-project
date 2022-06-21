import { Category, Dashboard, Inventory, Search } from "@mui/icons-material";

export const navLinks = [
    {
        name: "Dashboard",
        path: "/",
        icon: <Dashboard />

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
    }
];