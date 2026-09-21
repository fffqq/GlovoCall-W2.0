import {useEffect, useRef, useState} from "react";


export default function Carousel() {
     interface Restaurant{
         id: string;
         name: string;
         imagePath: string;
     }

    const[restaurant,setRestaurant]=useState<Restaurant[]>([]);
    const [error, setError] = useState();
    const scrolRef=useRef<HTMLUListElement>(null);
    useEffect(()=> {
        fetch('http://localhost:8080/api/v1/GlovoCall/listOfRest')
            .then(res => {
                if (!res.ok) {
                    throw new Error(`Server error: ${res.status}`);
                }
                return res.json();
            })
            .then(data=>setRestaurant(data))
            .catch(err => setError(err));
    },[])
    const scroll=(direction:'left'|'right') => {
        if(scrolRef.current){
            const {scrollLeft,clientWidth} = scrolRef.current;

            const scrollTo=direction==='left'
                ?scrollLeft-clientWidth
                :scrollLeft+clientWidth;

            scrolRef.current.scrollTo({left:scrollTo ,behavior:'smooth'});
        }
    }
    if (error) return (
        <div className="flex w-full min-h-1/3 bg-amber-100 justify-center items-center">
            <span className="text-xl h-20">Error:The restaurants did not load </span>
        </div>);
    return (
        <div className="flex justify-center items-center w-full min-h-[50vh] bg-amber-300 py-8 px-4">


            <div className="w-full bg-amber-300 ">
                <div className="flex justify-end gap-2 mb-3">
                    <button
                        onClick={() => scroll('left')}
                        className="px-4 py-1 bg-white hover:bg-gray-50 rounded shadow font-medium"
                    >
                        &larr; Back
                    </button>
                    <button
                        onClick={() => scroll('right')}
                        className="px-4 py-1 bg-white hover:bg-gray-50 rounded shadow font-medium"
                    >
                        Forward &rarr;
                    </button>
                </div>
            <ul
                ref={scrolRef}
                className="flex w-full gap-4  overflow-x-auto snap-x snap-mandatory pb-4">

                <style>
                    {'ul::-webkit-scrollbar{display:none} }'}
                </style>


                {restaurant.map(restaurant => (
                    <li className="flex flex-col items-center w-200px shrink-0  w-1/4
                    bg-white rounded-2xl shadow-sm hover:shadow-xl hover:-translate-y-1
                    transition-all duration-300 snap-start p-4 cursor-pointer"
                        key={restaurant.id}>


                        <div className="flex justify-center items-center  ">
                            <img
                                className="h-50 object-fill "
                                src={restaurant.imagePath}
                                alt={restaurant.name}
                            />
                        </div>
                        <p className="mt-4 text-center font-bold text-gray-800 text-lg w-full truncate">
                            {restaurant.name}
                        </p>
                    </li>
                ))}
            </ul>
            </div>
        </div>
    );
}