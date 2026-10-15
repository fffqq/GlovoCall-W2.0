import "./App.css";
import {useState} from "react";


type LoginElementProps = {
    isOpen: boolean;
    onClose: () => void;
};


export default function LoginElement({isOpen, onClose}:LoginElementProps)
{
    const [data,setData]=useState<string>("");


    if(!isOpen)
    return (null);
    return(
        <div className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-[90%] max-w-md bg-white rounded-2xl shadow-2xl p-8 flex flex-col gap-5 z-50">

            <h2 className="text-2xl font-bold text-gray-800 text-center mb-2">
                Enter details
            </h2>

            <div className="flex flex-col gap-4">
                <input
                    placeholder="Name"
                    type="text"
                    className="input-primary"
                    value={data}
                    onChange={(e) => setData(e.target.value)}
                />
                <input
                    placeholder="Email"
                    type="email"
                    className="input-primary"
                />
                <input
                    placeholder="Address"
                    type="text"
                    className="input-primary"/>
            </div>

            <div className="flex flex-row gap-3 mt-4">
                <button
                    onClick={() => console.log('Submit clicked')}
                    className="flex-1 bg-yellow-400 hover:bg-yellow-500 text-black font-semibold py-3 px-4 rounded-lg transition-colors"
                >
                    Submit
                </button>
                <button
                    onClick={onClose}
                    className="flex-1 bg-gray-100 hover:bg-gray-200 text-gray-700 font-semibold py-3 px-4 rounded-lg transition-colors"
                >
                    Close
                </button>
            </div>
        </div>
        );
        };