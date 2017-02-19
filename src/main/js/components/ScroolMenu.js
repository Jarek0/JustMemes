import React from 'react';


export default class ScroolMenu extends React.Component{

    constructor(props) {
        super(props);
        this.state={countOfPages: 0,numberOfPage: 0};
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
        return ((value==this.state.numberOfPage) ?'active':'default');
    }

    click(e) {
        var pageNumber=e.target.getAttribute('data-value');
        this.setState({numberOfPage  : pageNumber});
        this.props.showMemesFromPage(pageNumber);
    }

    render(){
        var pagesNumbers = [];
        for (var i=0; i < this.state.countOfPages; i++) {
            pagesNumbers.push(<a className={this.isActive(i)} key={'pageNumber-'+i} data-value={i} onClick={this.click.bind(this)}>{i+1}</a>);
        }
        return (
            <div className="mcs-horizontal-example">
                {pagesNumbers}
            </div>

        );
    }
};
