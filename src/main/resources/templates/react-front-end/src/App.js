import logo from './logo.svg';
// import './App.css';
import RightNav from './RightNav';
// import TopNav from './TopNav';
import TopNav from './KTopNav';
import LeftNav from './LeftNav';
import Footer from './Footer';
import Content from './Content';
import KContent from './Kontent';
import {BrowserRouter, Routes, Route} from "react-router-dom"
import EditPost from './EditPost';
import Main from './KMain';
import KIndex from './KIndex';
import { createContext,  useEffect,  useState } from 'react';

import {UserContext} from './UserContext'

function App() {
const [user,setUser]=useState({id:1,name:"jaden"});
const [postcontext, setPostcontext]=useState();
const [relatedcontext, setRelatedcontext]=useState();


  // create router here
  return (
    <UserContext.Provider value={{user,postcontext,setPostcontext,relatedcontext,setRelatedcontext}}>
    <div className=''>
     {/* Add BrowserRouter */}
     <BrowserRouter>
      <Routes>
        <Route path='/konsole' element={<KContent/>}>
        <Route index element={<KIndex/>}/>
          
          <Route path='edit' element={<Main/>}/>
        </Route>
        <Route path='/:slug' element={<Content/>}>
          {/* Add subroutes */}
          {/* <Route path='/edit' element={<EditPost/>}/> */}
        </Route>
      </Routes>
     </BrowserRouter> 

    </div>
    </UserContext.Provider>
  );
}

export default App;
