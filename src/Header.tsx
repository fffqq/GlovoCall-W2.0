import glovoLogo from "./assets/GlovoCallLogo.png";
import {useEffect, useState} from "react";
import SignInSignOut from "./SignInSignOut.tsx";



export default function Header() {

    const [modal, setModal] = useState(false);
    const [authMod, setAuthMod] = useState<'signUp' | 'signIn'>('signUp');
    const [username, setUsername] = useState<string | null>(null);
    const handleOpen = (mode: 'signUp' | 'signIn') => {
        setAuthMod(mode);
        setModal(true);
    };

    const Auth= ()=>{
        fetch("http://localhost:8080/info-header",{
        method: "GET",
        credentials: "include",
    })
        .then(res => res.ok? res.json() : Promise.reject("Unauthorized"))
        .then(data =>setUsername(data.username))
        .catch(()=>setUsername(null));
    }
    useEffect(() => {
        Auth();
            }, []);
    return (

    <header className="flex flex-row min-h-50px justify-between min-w-screen">
        <img src={glovoLogo} alt="Glovo Call Logo" className="h-1/5 w-1/10"/>

        {username ? (
            <div>
                <p className="pr-10 pt-5">Hello {username}</p>
            </div>
        ) :
            <div className="flex gap-4">
                <button className="pr-5" onClick={()=>handleOpen('signUp')}>Sign up</button>
                <button className="pr-5" onClick={()=>handleOpen('signIn')}>Sign in</button>
            </div>
        }

        <SignInSignOut isOpen={modal} mode={authMod} onClose={() => setModal(false)}
            onSuccess={() => {
            setModal(false);
                Auth();
            }}
            />

    </header>
    );
}