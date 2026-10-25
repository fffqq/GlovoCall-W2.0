import "./App.css";
import {useState} from "react";
import * as React from "react";


type LoginElementProps = {
    isOpen: boolean;
    mode: 'signUp' | 'signIn';
    onClose: () => void;
    onSuccess: () => void;
};

type Data={
    name: string,
    email: string,
    password: string,
}
export default function SignInSignOut({isOpen, onClose,mode,onSuccess}:LoginElementProps) {
    const INITIAL_DATA = {
        name: "",
        email: "",
        password: ""
    };
    const [data, setData] = useState<Data>(INITIAL_DATA);

    const [error, setError] = useState("");

    const isSignUp = mode === 'signUp';

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setError("")
        setData(prev => ({...prev, [name]: value}));
    };
    const handleSubmit = async () => {
        if ((!data.email.trim() || !data.password.trim() || !data.name.trim())&&isSignUp) {
            setError("Please enter a valid data");
            setData(INITIAL_DATA);
            return;
        }
        const url=isSignUp? "http://localhost:8080/PostRest":
            "http://localhost:8080/auth";
        const bodyData=isSignUp
            ? {email:data.email, password:data.password,username:data.name}
            : {email:data.email, password:data.password};
        try {
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                credentials: "include",
                body: JSON.stringify (bodyData)
            });
            if (response.ok) {
                onClose();
                onSuccess();
            } else {
                setError("Wrong email or password");
                setData(INITIAL_DATA);
            }
        } catch (err) {
            setError("Network error. Try again later.");
        }
    }
        if (!isOpen)
            return null;
        return (
            <div
                className="fixed top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-[90%] max-w-md bg-white rounded-2xl shadow-2xl p-8 flex flex-col gap-5 z-50">

                <h2 className="text-2xl font-bold text-gray-800 text-center mb-2">
                    {isSignUp ? "Create Account" : "Welcome Back"}
                </h2>

                {error && <div className="bg-white text-center text-red-600">{error}</div>}


                <div className="flex flex-col gap-4">
                    {isSignUp &&(
                        <input
                        name="name"
                        placeholder="Username"
                        type="text"
                        className="input-primary"
                        required={true}
                        value={data.name}
                        onChange={handleChange}
                    />
                    )}
                    <input
                        name="email"
                        placeholder="Email"
                        type="email"
                        className="input-primary"
                        required={true}
                        value={data.email}
                        onChange={handleChange}
                    />
                    <input
                        name="password"
                        placeholder="Password"
                        type="text"
                        className="input-primary"
                        required={true}
                        value={data.password}
                        onChange={handleChange}
                    />
                </div>

                <div className="flex flex-row gap-3 mt-4">
                    <button
                        onClick={() => handleSubmit()}
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