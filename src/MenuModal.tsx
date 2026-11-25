import {useState} from "react";
import {useEffect} from "react";


interface Props {
nameOfRestaurant:string;
onClose: () => void;
}
interface MenuList {
    id: string;
    name: string;
    description?: string;
    price: number;
    brand_id: string;
    image_path: string;
}
export default function MenuModal(Props: Props) {
    const [menuList, setMenuList] = useState<MenuList[]>([]);
    const [error, setError] = useState("");
    useEffect(()=> {
        fetch(`https://localhost:7044/listForMenu/${encodeURIComponent(Props.nameOfRestaurant)  }`)
            .then(res => {
                if (!res.ok) {
                    throw new Error(`Server error: ${res.status}`);
                }
                return res.json();
            })
            .then(data=>setMenuList(data))
            .catch(err => setError(err.message));
    },[Props.nameOfRestaurant])

    return(<div
            onClick={Props.onClose}
            className=" fixed inset-0 z-50 flex items-center justify-center bg-black/50 ">


        <div

            className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-full max-w-3/5 max-h-3/5
            bg-white rounded-2xl shadow-2xl p-8 flex flex-col gap-5 z-50
              ">
            <div className="overflow-y-auto">
                {error ?(
                    <div>Sorry,error occured during loadig menu</div>
                ):

                    (<div onClick={event => event.stopPropagation()}>
                        <button className="block ml-auto px-4 py-2 rounded-xl bg-amber-50 text-amber-700 font-semibold
                         hover:bg-amber-100 transition cursor-pointer
                            mb-3">Close</button>
                        <ul className="grid grid-cols-1 md:grid-cols-3 gap-10">
                        {menuList.map(menu => (
                            <li key={menu.id}
                            className="flex flex-col justify-between items-center border border-gray-200 rounded-2xl
                             p-4 shadow-sm hover:shadow-md transition cursor-pointer shadow-amber-300">
                               <img src={menu.image_path} className="object-cover p-3 object-center
                               bg-gray-50 rounded-4xl w-3/4 h-36 mb-3"
                                    alt=""/>

                                <strong>{menu.name}</strong>
                                <span className="text-sm font-bold text-amber-600">{menu.price} €</span>
                            </li>
                        ))}
                    </ul>
                    </div>)



                }

                </div>
        </div>

    </div>
    );






}