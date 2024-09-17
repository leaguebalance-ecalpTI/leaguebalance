import {DefaultMenuList} from "../shared/types/default-menu-list";
import {environment} from "../../environments/environment";



export const menuOptions: DefaultMenuList[] = [
  {
    menuName: "Inicio",
    route: environment.PREFIX_BASE + "/inicio",
  } ,
  {
    menuName: "Jogadores",
    route: environment.PREFIX_BASE + "/players",
  },
];
