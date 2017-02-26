import React from 'react';
import { Link } from "react-router";


export default class ScroolMenu extends React.Component{

    constructor(props) {
        super(props);
        this.state={countOfPages: 0};
    }

    componentDidMount(){
        this.getPagesCount();
    }

    getPagesCount(){

        $.ajax({
            url: '/mem/getPagesCount',
            type: 'GET'
        }).then(function(data) {
            this.setState({ countOfPages:data });
        }.bind(this))

    }

    isActive(value){
        return ((value==this.props.numberOfPage) ?'active':'default');
    }


    render(){
        var pagesNumbers = [];
        for (var i=1; i < this.state.countOfPages+1; i++) {
            pagesNumbers.push(<Link className={this.isActive(i)} key={'pageNumber-'+i} to={'/'+(i)}>{i}</Link>);
        }
        return (
            <div className="mcs-horizontal-example">
                {pagesNumbers}
            </div>

        );
    }
};
