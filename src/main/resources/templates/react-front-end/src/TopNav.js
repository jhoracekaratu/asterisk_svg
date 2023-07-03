import { UserContext } from "./UserContext";
import { useContext } from "react";
function TopNav(){
  const userContext = useContext(UserContext);
    return(
    <>
     <nav className="navbar navbar-expand-lg navbar-light bg-light ">
     <div className="container-fluid">
        <a className="navbar-brand" href="">
        <img src="./logo.png" alt="" width="40" height="40" className="d-inline-block align-text-top"/>
            &nbsp;&nbsp;
        Javaguidings.com</a>
        <form className="d-flex">
      <button className="btn btn-outline-success" type="submit">Login</button>
    </form>
     </div>

     </nav>
{/* Algorithm to display dynamic categories related to the current topic */}
   <ul className="nav justify-content-center">
   {userContext.relatedcontext!=undefined &&Array.prototype.map.call(userContext.relatedcontext,
   (x)=>{
      return <li key={x.id} className="nav-item"><a className="nav-link"  href={x.slug}>{x.title}</a> </li>
   
   })
   }

   </ul>

       
    </>
    
    );
    }
    export default TopNav;